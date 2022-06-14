pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven_3.8.6"
    }

    stages {
        stage('Build') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jtian30/product-service.git']]])
                // Run Maven on a Unix agent.
                sh "mvn clean install"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Build Docker image') {
            steps {
                script {
                    sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t jtian30/product-service .'
                }
            }
        }
        stage('push image to Docker Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                        sh '/Applications/Docker.app/Contents/Resources/bin/docker login -u jtian30 -p ${dockerhubpwd}'
                        sh '/Applications/Docker.app/Contents/Resources/bin/docker push jtian30/product-service'
                    }
                }
            }
        }
    }
}
