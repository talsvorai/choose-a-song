# choose-a-song

This application allows you to choose your favourite song (out of three songs) and display it's lyrics on your screen.
To run it you must enter a valid url that runs the application.

### **Steps to set up the application:**  

#### **Create the source code**  
1. Create the code in a structure that allows compilation and testing via Maven 
2. Create a Github repository and upload the source code onto it 
3. Try running the application locally (using port 8080) and check if the application runs in your browser

#### **Create the pipeline**
1. Install Java and Docker on an EC2 instance and run a Jenkins image on it. Make it the master node
2. Install Java and Docker on an EC2 instance and make it the agent node
3. Create a Jenkinsfile that configures the steps for the CI/CD pipeline
4. Install the default plugins and add Maven Integration and Slack Notification Plugin

*Make sure to configure GitHub, the agent node and the inbound traffic in aws*


