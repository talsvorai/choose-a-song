# choose-a-song

This application allows you to choose your favourite song (out of three songs) and display it's lyrics on your screen.

This is a web application that runs via the internet.

## **Steps to set up the application and run it:**  

#### **Create the source code**  
1. Create the code in a structure that allows compilation and testing via Maven .
2. Create a Github repository and upload the source code onto it.
3. Try running the application locally (using port 8080) and check if the application runs in your browser.

#### **Create the pipeline**
1. Install Java and Docker on an EC2 instance and run a Jenkins image on it. Make it the master node.
2. Install Java and Docker on an EC2 instance and make it the agent node.
3. Create a Jenkinsfile that configures the steps for the CI/CD pipeline.
4. Install the default plugins and add Maven Integration and Slack Notification Plugin.

*Make sure to configure GitHub, the agent node and the inbound traffic in aws.*

#### **Create the pipeline**
1. Install Java and Docker on an EC2 instance and run a Jenkins image on it. Make it the master node.
2. Install Java and Docker on an EC2 instance and make it the agent node.
3. Create a Pipeline job in jenkins
4. Create a Jenkinsfile that configures the steps for the CI/CD pipeline.
4. Install the default plugins and add GitHub plugin, Maven Integration and Slack Notification Plugin.

*Make sure to configure GitHub, the agent node and the inbound traffic in aws.*

#### **Checkout the code using git, Package and test the code via Maven**
1. Make sure Maven and Git are installed in the agent and the paths are configured
2. Checkout the source code - configure it so the job runs every time a code is pushed into the main branch (GitHub webhook).
3. Build the jar file and test it using maven - the test validates HTTP connection to the application.

#### **Build a docker image, test it and push to Dockerhub**
1. Create a Dockerfile that build a slim image that is exposed on port 8080
2. Build docker image and test that it runs and stops as expected.  
3. Upload the image to Dockerhub using the relevant credentials

#### **Deploy the application via another EC2 instance**
1. Enter the instance using ssh
2. Pull the latesr image from Dockerhub
3. Run the container while exposing port 8080

### **At this point you can insert the deployment instance's url (port 8080) and access the application**

## **To provision an EC2 instance and run the application on it:** 





