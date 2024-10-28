pipeline {
    agent any

    environment {
            DOCKER_IMAGE = 'bangmingging/jle-member-service:latest'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/shoppingmall-platform/member-service.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com/', 'DOCKER_HUB_CREDENTIALS_MK') {
                        def image = docker.image("${DOCKER_IMAGE}")
                        sh "docker buildx create --use --name multiarch"
                        sh """
                        docker buildx build \
                        --platform linux/amd64,linux/arm64 \
                        -t ${image.imageName()} \
                        --push .
                        """
                    }
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                sshagent(['SSH_CREDENTIALS']) {
                    withCredentials([
                        usernamePassword(credentialsId: 'GITHUB_CREDENTIALS', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS'),
                        usernamePassword(credentialsId: 'DOCKER_HUB_CREDENTIALS_MK', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')
                    ]) {
                        sh """
                        # Docker login
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                        # SSH and deployment commands
                        ssh -o StrictHostKeyChecking=no ubuntu@${DEPLOY_SERVER} '
                            cd docker-compose &&
                            git config credential.helper store &&
                            git pull https://${GIT_USER}:${GIT_PASS}@github.com/shoppingmall-platform/docker-compose.git main &&
                            docker pull ${DOCKER_IMAGE} &&
                            docker compose down &&
                            docker compose up -d
                        '
                        """
                    }
                }
            }
        }
    }
}