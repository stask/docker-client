(ns docker-client.core
  (:import
   (com.spotify.docker.client DockerClient
                              DockerClient$ListContainersParam
                              DefaultDockerClient)))

(defn make-client
  ([]
   (.build (DefaultDockerClient/fromEnv)))
  ([url]
   (DefaultDockerClient. url)))

(defn list-containers
  ([] (list-containers false))
  ([all?]
   (let [params (into-array DockerClient$ListContainersParam
                            (if all?
                              [(DockerClient$ListContainersParam/allContainers)]
                              []))]
     (.listContainers client params))))
