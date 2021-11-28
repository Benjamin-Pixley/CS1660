from flask import Flask, render_template, request

app = Flask(__name__)
@app.route('/')
def hello():
	addr = request.host_url
	host = addr[:-5]
	jup = host + "8888"
	sp = host + "8889"
	sq = host + "9000"
	return render_template('template.html', jupyter=jup, spark=sp, qube=sq)

if __name__ == '__main__':
	app.run(host="0.0.0.0",port=5000)