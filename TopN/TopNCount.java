package hadoop;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TopNCount {
	public static class mapper extends Mapper<Object, Text, Text, IntWritable>{
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		@Override
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
			StringTokenizer itr = new StringTokenizer(value.toString());
			while(itr.hasMoreTokens()){
				word.set(itr.nextToken());
				context.write(word,one);
			}
		}
	}

	public static class reducer extends Reducer<Text, IntWritable, Text, IntWritable>{
		private TreeMap<Integer,Text> treemap = new TreeMap<Integer,Text>();
		private int n = 5;
		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;

			for(IntWritable val : values) {
				sum += val.get();
			}
			treemap.put(sum,key);
			if(treemap.size()>n){
				treemap.remove(treemap.firstKey());
			}
		}
		@Override
		public void cleanup(Context context) throws IOException, InterruptedException {
			for(Map.Entry<Integer,Text> entry : treemap.entrySet()){
				int  res = entry.getKey();
				Text word = entry.getValue();
				context.write(word, new IntWritable(res));
			}
		}
	}

	public static void main(String[] args)  throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"word count");
		job.setJarByClass(TopNCount.class);
		job.setMapperClass(mapper.class);
		job.setReducerClass(reducer.class);
		job.setCombinerClass(reducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}