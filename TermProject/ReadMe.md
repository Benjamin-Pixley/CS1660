NOTE: YOU MUST FIRST CREATE A PROJECT. MY PROJECT ID WAS termproject-333202
IF THIS IS NOT YOUR PROJECT ID, THEN YOU WILL NEED TO ADJUST THE YAML FILES BY REPLACING MY PROJECT ID WITH YOUR PROJECT ID
I UNDERSTAND YOU DID NOT WANT THE USER TO HAVE TO DO ANY OF THIS, BUT YOU ARE NOT ABLE TO SIMPLY USE A $PROJECT_ID PLACEHOLDER
TO AUTIMATICALLY INPUT THE PROJECT_ID. PLEASE DO NOT MISS THIS

DISCLAIMER: GOOGLE CLOUD SHELL WOULD NOT LET MY DOWNLOAD THE YAML FILES. SO THEY WERE RETYPED ON A LOCAL FILE. I DID MY BEST TO SCAN FOR ANY MISTAKES/TYPOS
I APOLOGIZE IF I MISSED ANY OF THEM. THANK YOU FOR UNDERSTANDING

Steps to deploy my application:
1. git clone this repository into your gcp console (only the multi-port.yaml and service.yaml files are relevant)
2. Create a cluster with the default settings or use the following command:
  gcloud beta container --project "termproject-333202" clusters create "cluster-1" --zone "us-central1-c" --no-enable-basic-auth --cluster-version "1.21.5-gke.1302" --release-channel "regular" --machine-type "e2-medium" --image-type "COS_CONTAINERD" --disk-type "pd-standard" --disk-size "100" --metadata disable-legacy-endpoints=true --scopes "https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append" --max-pods-per-node "110" --num-nodes "3" --logging=SYSTEM,WORKLOAD --monitoring=SYSTEM --enable-ip-alias --network "projects/termproject-333202/global/networks/default" --subnetwork "projects/termproject-333202/regions/us-central1/subnetworks/default" --no-enable-intra-node-visibility --default-max-pods-per-node "110" --no-enable-master-authorized-networks --addons HorizontalPodAutoscaling,HttpLoadBalancing,GcePersistentDiskCsiDriver --enable-autoupgrade --enable-autorepair --max-surge-upgrade 1 --max-unavailable-upgrade 0 --enable-shielded-nodes --node-locations "us-central1-c"
4. docker pull crashpixley/test-image
5. docker tag crashpixley/test-image gcr.io/$PROJECT_ID/test-image
6. docker push gcr.io/$PROJECT_ID/test_image
7. docker pull jupyter/minimal-notebook
8. docker tag jupyter/minimal-notebook gcr.io/$PROJECT_ID/jupyter-notebook
9. docker push gcr.io/$PROJECT_ID/jupyter-notebook
10. docker pull jupyter/all-spark-notebook
11. docker tag jupyter/all-spark-notebook gcr.io/$PROJECT_ID/spark
12. docker push gcr.io/$PROJECT_ID/spark
13. docker pull sonarqube
14. docker tag sonarqube gcr.io/$PROJECT_ID/sonarqube
15. docker push gcr.io/$PROJECT_ID/sonarqube
16. kubectl apply -f multi-port.yaml
17. kubectl apply -f service.yaml
18. This concludes how to deploy the application

Utilizing the application:
1. Run "kubectl get svc"
2. Wait a minute and run it again if the external ip says <pending>
3. Navigate to http://$EXTERNAL_IP:5000 to go to the flask app gui (this may take a minute to load so just wait a minute or two if it doesn't initially load"
4. You can click on any of the buttons to navigate to the app

Project Notes:
- I found a hadoop file that worked on my local machine but I was having issues with creating the correct environment variables on GKE so that unfortunately doesnt work

  
Flask GUI:
![image](https://user-images.githubusercontent.com/78111942/143733163-175689ce-9bad-4b4d-8aa7-a72d7f348be1.png)
  
Thanks to these online resources for guiding me through the project:
flask-> https://kekayan.medium.com/a-step-by-step-guide-for-deploying-flask-web-services-on-gke-cluster-76420d75671d
multiport listening -> https://stackoverflow.com/questions/45147664/how-to-expose-multiple-port-using-a-load-balancer-services-in-kubernetes
multicontainer deployment -> https://kubernetes.io/docs/concepts/workloads/controllers/deployment/
flask templates -> https://stackoverflow.com/questions/12096522/render-template-with-multiple-variables

Video Link:

  
Links for containers:
https://hub.docker.com/repository/docker/crashpixley/test-image
https://hub.docker.com/r/jupyter/minimal-notebook
https://hub.docker.com/r/jupyter/all-spark-notebook
https://hub.docker.com/_/sonarqube
https://hub.docker.com/r/bde2020/hadoop-datanode
https://hub.docker.com/r/bde2020/hadoop-namenode
https://hub.docker.com/r/bde2020/hadoop-nodemanager
https://hub.docker.com/r/bde2020/hadoop-resourcemanager
https://hub.docker.com/r/bde2020/hadoop-historyserver
