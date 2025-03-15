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

                    // Run the Docker container in detached mode and capture the container ID
                    def containerId = sh(script: 'sudo docker run -d -p 8080:8080 talsvorai/choose-a-song:artifact-${BUILD_NUMBER}', returnStdout: true).trim()
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
                        sh '''
                        ssh -i $SSH_KEY -o StrictHostKeyChecking=no $SSH_USER@13.53.216.126 << 'EOF'
                            echo "Pulling latest Docker image..."
                            docker pull talsvorai/choose-a-song:artifact-${BUILD_NUMBER}

                            echo "Stopping and removing old container..."
                            docker stop choose-a-song || true
                            docker rm choose-a-song || true

                            echo "Running new container..."
                            docker run -d --name choose-a-song -p 8080:8080 talsvorai/choose-a-song:artifact-${BUILD_NUMBER}
                        EOF
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