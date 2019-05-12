pipeline {
    agent any

    stages {
        stage('Prepare environment') {
            steps {
                echo 'Add gradle with version...'
                sh 'gradle wrapper --gradle-version=4.4.1'
            }
        }
        stage('Create Database') {
            steps {
                echo 'Create Database...'
                sh './gradlew createDatabase'
            }
        }
        stage('Build and Test') {
            steps {
                echo 'Build...'
                sh './gradlew clean build'
            }
        }
        stage('Sonarqube') {
            steps {
                echo 'Sonarqube...'
                sh './gradlew sonarqube'
            }
        }
    }
}
