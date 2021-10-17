#!/usr/bin/env groovy
node {

    environment {
        registry = "panduboyina/airport-app-1.0-snapshot"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }


        stage('Checkout') {

                checkout scm

        }

        stage('Compile Stage') {

                echo "compile the  code"
                sh "mvn clean verify"



        }
       stage("build & SonarQube analysis") {
        node {
            withSonarQubeEnv('My SonarQube Server') {
                sh 'mvn clean package sonar:sonar'
            }
        }
    }

        stage("Quality Gate"){
        timeout(time: 1, unit: 'HOURS') {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
        }
    }

        stage('Build image') {

                script {
                   // docker.withRegistry('https://registry.hub.docker.com', ":$registryCredential")
                     dockerImage = docker.build("panduboyina/bankingapp-2.0-snapshot:${BUILD_NUMBER}")
                }

        }
        stage('Test') {

                echo 'Testing..'

        }

        stage('Deploy image') {

                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        dockerImage.push()
                    }


            }
        }
        stage('Publish Image') {

                echo 'publish image into kubernates....'

        }

}