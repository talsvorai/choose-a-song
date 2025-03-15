pipeline {
    agent any

    environment {
        DOCKERHUB_CRED = credentials('dockerhub_cred')
        DEPLOY_IP = "13.53.216.126"
        APP_NAME = "choose-a-song"
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
                    sh 'sudo docker build -t ${DOCKERHUB_CRED_USR}/${APP_NAME}:artifact-${BUILD_NUMBER} .'
                    echo "Docker image built successfully"

                    //Running docker container
                    sh 'sudo docker run -d --name ${APP_NAME} -p 8080:8080 ${DOCKERHUB_CRED_USR}/${APP_NAME}:artifact-${BUILD_NUMBER}'
                    echo "Docker container '${APP_NAME}' started successfully"

                    // Stop the Docker container
                    sh 'sudo docker stop ${APP_NAME}'
                    echo "Docker stopped successfully"

                    // Remove the Docker container
                    sh 'sudo docker rm ${APP_NAME}'
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
                    // Push the Docker image to Docker Hub with a build-specific tag
                    sh 'sudo docker push ${DOCKERHUB_CRED_USR}/${APP_NAME}:artifact-${BUILD_NUMBER}'

                    // Now tag the latest build as 'latest'
                    echo "Tagging the Docker image as 'latest'"
                    sh 'sudo docker tag ${DOCKERHUB_CRED_USR}/${APP_NAME}:artifact-${BUILD_NUMBER} ${DOCKERHUB_CRED_USR}/${APP_NAME}:latest'

                    // Push the 'latest' tag to Docker Hub
                    sh 'sudo docker push ${DOCKERHUB_CRED_USR}/${APP_NAME}:latest'
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
                        ssh -i $SSH_KEY -o StrictHostKeyChecking=no $SSH_USER@${DEPLOY_IP} "echo 'Pulling latest Docker image...'
                        sudo docker pull ${DOCKERHUB_CRED_USR}/${APP_NAME}:latest

                        echo 'Stopping and removing old container - must be initialized in machine'
                        sudo docker stop ${APP_NAME}
                        sudo docker rm ${APP_NAME}

                        echo 'Running new container...'
                        sudo docker run -d --name ${APP_NAME} -p 8080:8080 ${DOCKERHUB_CRED_USR}/${APP_NAME}:latest"
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'The job had passed successfully!'
            slackSend channel: '#devops-alerts', message: "Successful run for build ${BUILD_NUMBER}", tokenCredentialId: 'final_slack'
        }
        failure {
            echo 'The job has failed!'
            slackSend channel: '#devops-alerts', message: "Unsuccessful run for build ${BUILD_NUMBER}", tokenCredentialId: 'final_slack'
        }
    }
}