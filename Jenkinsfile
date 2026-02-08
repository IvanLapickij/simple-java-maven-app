// Selenium tests are executed only when enabled using a Jenkins parameter.

pipeline {
    agent any
    tools {
        maven 'Maven_3'
    }

    parameters {
        booleanParam(
            name: 'RUN_UI_TESTS',
            defaultValue: false,
            description: 'Run Selenium UI tests'
        )
    }

    stages {
        // SELENIUM STAGE
        stage('UI Tests (Selenium)') {
            when {
                expression { return params.RUN_UI_TESTS }
            }
            steps {
                sh 'mvn -B verify -DskipUnitTests=true'
            }
        }

        // JENKINS STAGE
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // BUILD AND TEST STAGE
        stage('Build and Test') {
            steps {
                bat 'mvn clean test package'
            }
        }
    }

    // POST ACTIONS
    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml,target/failsafe-reports/*.xml'
        }
    }
}
