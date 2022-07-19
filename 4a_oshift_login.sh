source ./0_env_oshift.sh

oc login --token=$OC_TOKEN --server=$OC_SERVER
oc project devenablement-tekton-pilot
