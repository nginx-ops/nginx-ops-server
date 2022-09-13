def TARGET_PATH = '/www/server/nginx-ops-server'
def SOURCE_PATH = './nginx-ops-admin/target'
def SHELL_NAME = 'nginx-ops-server.sh'

pipeline {
    agent any

    tools {
        maven 'maven-3.6.3'
    }

    stages {
        stage('getGitlabBranchName') {
            steps {
                echo "current branch is: ${env.gitlabBranch}"
            }
        }

        stage('build-test') {
            when {
                environment name: 'gitlabBranch', value: 'test'
            }
            steps {
                withEnv(['JENKINS_NODE_COOKIE=dontkillme']) {
                    sh "mvn clean package -U -Ptest -DskipTests"
                    sh "cp $SOURCE_PATH/*.jar $TARGET_PATH"
                    sh "cp $SHELL_NAME $TARGET_PATH"
                    sh "sh $TARGET_PATH/$SHELL_NAME restart"
                }
            }
        }
    }
}