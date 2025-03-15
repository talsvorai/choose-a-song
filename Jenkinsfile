pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven' // Assumes Maven is installed and configured in Jenkins
        PATH = "${MAVEN_HOME}/bin:${env.PATH}" //Updating path to use maven
        DOCKERHUB_CRED = credentials('dockerhub_cred')
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
                    sh 'sudo docker build -t talsvorai/choose-a-song:artifact-${BUILD_NUMBER} .'
                    echo "Docker image built successfully"

                    //Running docker container
                    sh 'sudo docker run -d --name choose-a-song -p 8080:8080 talsvorai/choose-a-song:artifact-${BUILD_NUMBER}'
                    echo "Docker container 'choose-a-song' started successfully"

                    // Stop the Docker container
                    sh 'sudo docker stop choose-a-song'
                    echo "Docker stopped successfully"

                    // Remove the Docker container
                    sh 'sudo docker rm choose-a-song'
                    echo "Docker stopped successfully"
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {

                    // Login to Docker Hub using Jenkins credentials
                    sh '''
                    echo ${DOCKERHUB_CRED_PSW} | sudo docker login -u ${DOCKERHUB_CRED_USR} --password-stdin
                    '''

                    echo "Pushing Docker image"
                    // Push the Docker image to Docker Hub
                    sh 'sudo docker push talsvorai/choose-a-song:artifact-${BUILD_NUMBER}'
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'deploy_cicd_key_username', keyFileVariable: 'SSH_KEY', usernameVariable: 'SSH_USER')]) {
                    script {
                        echo "Build number: ${BUILD_NUMBER}"
                        sh '''
                        echo "Build number: ${BUILD_NUMBER}"
                        ssh -i $SSH_KEY -o StrictHostKeyChecking=no $SSH_USER@13.53.216.126 "echo 'Pulling latest Docker image...'
                        sudo docker pull talsvorai/choose-a-song:artifact-${BUILD_NUMBER}

                        echo 'Stopping and removing old container - must be initialized in machine'
                        sudo docker stop choose-a-song || true
                        sudo docker rm choose-a-song || true

                        echo 'Running new container...'
                        sudo docker run -d --name choose-a-song -p 8080:8080 talsvorai/choose-a-song:artifact-${BUILD_NUMBER}"
                        '''
                    }
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