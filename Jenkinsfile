pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat 'C:\\Users\\ivanl\\Documents\\apache-maven-3.9.10-bin\\apache-maven-3.9.10\\bin\\mvn.cmd clean test package'
            }
        }
    }
}
