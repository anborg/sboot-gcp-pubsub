# Create a k8 culster that I can use with podman
# https://kind.sigs.k8s.io/docs/user/quick-start/#installation

#creates a default cluster named 'kind'
kind create cluster kind
#
kind get clusters
#
kubectl cluster-info --context kind-kind



#kn install in kind
kubectl apply -f https://github.com/knative/operator/releases/download/knative-v1.6.0/operator.yaml
kubectl config set-context --current --namespace=default
kubectl get deployment knative-operator
#check LOG
#kubectl logs -f deploy/knative-operator
kubectl apply -f knative-serving.yaml

# Check courier
kubectl --namespace knative-serving get service kourier
#Chek Kourier deployment
kubectl get deployment -n knative-serving

#dns
kubectl apply -f https://github.com/knative/serving/releases/download/knative-v1.6.0/serving-default-domain.yaml
