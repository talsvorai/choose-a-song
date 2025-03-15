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
                    sh 'sudo mvn clean package -DskipTests'
                    echo "Build was successful"
                }
            }
        }

        stage('Maven_Test') {
            steps {
                script {
                    echo "Running test for connection"
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
                    sh 'sudo docker build -t talsvorai/choose-a-song:${BUILD_NUMBER} .'
                    echo "Docker image built successfully"

                    // Run the Docker container in detached mode and capture the container ID
                    def containerId = sh(script: 'sudo docker run -d -p 8080:8080 talsvorai/choose-a-song:${BUILD_NUMBER}', returnStdout: true).trim()
                    echo "Docker container started successfully with ID: ${containerId}"

                    // Stop the Docker container using its ID
                    sh "sudo docker stop ${containerId}"
                    echo "Docker stopped successfully"
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {

                    echo "Pushing Docker image"
                    // Push the Docker image to Docker Hub
                    sh 'sudo docker push talsvorai/choose-a-song:${BUILD_NUMBER}'
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