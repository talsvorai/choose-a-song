pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven' // Assumes Maven is installed and configured in Jenkins
        PATH = "${MAVEN_HOME}/bin:${env.PATH}" //Updating path to use maven
    }


    stages {
        stage('Checkout') {
            steps {
                script {
                    // Clone the repository from GitHub
                    checkout scm
                }
            }
        }

        stage('Maven Build') {
            steps {
                script {
                    echo "This is build stage"
                    sh 'sudo mvn clean package'
                    echo "Build was successful"
                }
            }
        }

        stage('Maven_Test') {
            steps {
                script {
                    echo "This is test stage"
                    sh 'sudo mvn test'
                    echo "Test was successful"
                }
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    echo "Building Docker image"
                    // Build the Docker image
                    //sh 'sudo docker build -t java-calculator:${BUILD_NUMBER} .'
                    //echo "Docker image built successfully"
                }
            }
        }
    }

    post {
        success {
            echo 'Build and tests passed successfully!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}