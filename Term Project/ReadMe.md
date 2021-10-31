Steps to connect to GCP:
  1.  First you need to setup a project with a project ID
  2.  Get your authentication key so that you can access gcp project
  3.  Build a storage bucket for your image
  4.  Upload data selected by user to the bucket
  5.  Run your deployed image on the uploaded data

Steps to run the client side application:
  Note: I am unsure if you mean just the base program or the docker image containing the program
  Docker Container:
    1. docker build -t userInteraction .
    2. docker run userInteraction
  Java Program:
    1. Javac userInteraction.java
    2. Java userInteraction
