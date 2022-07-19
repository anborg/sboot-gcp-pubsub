source ./0_env_oshift.sh
oc delete -f kn-app-deploy.yaml
oc apply -f kn-app-deploy.yaml
kn service list
kn service describe kn-demo-quarkus-rest
oc logs deployment/kn-demo-quarkus-rest-v1-deployment
