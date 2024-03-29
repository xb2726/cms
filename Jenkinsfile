pipeline {
    agent {
        node {
            label 'maven'
        }
    }

    parameters {
        string(name: 'TAG_NAME', defaultValue: '', description: '镜像标签(v.*用于发布正式版)')
    }

    environment {
        DOCKER_CREDENTIAL_ID = 'harbor-id'
        REGISTRY = 'hub.app-chengdu1.yunzhicloud.com'
        DOCKERHUB_NAMESPACE = 'hainan'
        APP_NAME = 'hainan-monitor'
        GITLAB_CREDENTIAL_ID = 'gitlab-id'
        GIT_URL = 'gitlab.yunzhicloud.com/hainan/hainan-monitor.git'
        GIT_EMAIL = 'devops@yzcloud.com'
        // SONAR_CREDENTIAL_ID = 'sonar-token'
        KUBECONFIG_CREDENTIAL_ID = 'kube-id'
    }

    stages {
        stage('checkout scm') {
            steps {
                checkout(scm)
            }
        }

        // stage('unit test') {
        //     steps {
        //         container('maven') {
        //             sh 'mvn clean -gs `pwd`/config/settings.xml test'
        //         }
        //     }
        // }

//        stage('sonarqube analysis') {
//            steps {
//                container('maven') {
//                    withCredentials([string(credentialsId: "$SONAR_CREDENTIAL_ID", variable: 'SONAR_TOKEN')]) {
//                        withSonarQubeEnv('sonar') {
//                            sh "mvn sonar:sonar -o -gs `pwd`/configuration/settings.xml -Dsonar.branch=$BRANCH_NAME -Dsonar.login=$SONAR_TOKEN"
//                        }
//                    }
//                    timeout(time: 1, unit: 'HOURS') {
//                        waitForQualityGate abortPipeline: true
//                    }
//                }
//            }
//        }

        stage('build & push') {
            steps {
                container('maven') {
                    sh 'mvn -Dmaven.test.skip=true -gs `pwd`/config/settings.xml clean package'
                    sh 'docker build -f Dockerfile -t $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER .'
                    withCredentials([usernamePassword(passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME', credentialsId: "$DOCKER_CREDENTIAL_ID",)]) {
                        sh 'echo "$DOCKER_PASSWORD" | docker login $REGISTRY -u "$DOCKER_USERNAME" --password-stdin'
                        sh 'docker push $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER'
                    }
                }
            }
        }

        stage('push latest') {
            when {
                branch 'master'
            }
            steps {
                container('maven') {
                    sh '(docker rmi $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:latest || echo "not exist")'
                    sh 'docker tag $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:latest'
                    sh 'docker push $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:latest && docker rmi $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:latest'
                }
            }
        }

        stage('deploy to test') {
            when {
                branch 'master'
            }
            steps {
                // input(id: 'deploy-to-test', message: '发布到测试环境?')
                kubernetesDeploy(configs: 'config/deploy-test/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
            }
        }
        stage('push with tag') {
            when {
                expression {
                    return params.TAG_NAME ==~ /v.*/
                }
            }
            steps {
                container('maven') {
                    input(id: 'release-image-with-tag', message: '发布镜像TAG?')
                    withCredentials([usernamePassword(credentialsId: "$GITLAB_CREDENTIAL_ID", passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                        sh 'git config --global user.email "$GIT_EMAIL"'
                        sh 'git config --global user.name "$GIT_USERNAME"'
                        sh 'git tag -a $TAG_NAME -m "$TAG_NAME"'
                        sh 'git push https://$GIT_USERNAME:$GIT_PASSWORD@$GIT_URL --tags --ipv4'
                    }
                    sh 'docker tag $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:$TAG_NAME '
                    sh 'docker push $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:$TAG_NAME && docker rmi $REGISTRY/$DOCKERHUB_NAMESPACE/$APP_NAME:$TAG_NAME'
                }
            }
        }
        stage('deploy to prod') {
            when {
                expression {
                    return params.TAG_NAME ==~ /v.*/
                }
            }
            steps {
                input(id: 'deploy-to-prod', message: '发布到正式环境?')
                kubernetesDeploy(configs: 'config/deploy-prod/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
            }
        }
    }
}