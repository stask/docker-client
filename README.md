# docker-client

This is a Clojure wrapper for [Spotify's docker-client library](https://github.com/spotify/docker-client).

## Why not to use the java library directly?

Well, the docker-client library is cool, but it's not very Clojure-friendly because it uses varargs in multple places, inner classes, etc.
This wrappers also simplifies the API because it only exposes the subset of the library i'm currently using.

## Usage

If you want to connect to local docker (via unix socket):

``` clojure
(def client (make-client))
```

If you want to connect to a remote docker instance, one of the options is to run `socat` to expose the docker's unix socket via TCP, open SSH tunnel to it, and then connect to the local end of the tunnel.

On the server where the docker is running, run this:

``` shell
docker run -v /var/run/docker.sock:/var/run/docker.sock -d --name socat -p 2375:2375 anthonydahanne/docker-socat
```

Then open the SSH tunnel from your local machine:

``` shell
ssh -f <user>@<remote-host> -L 2375:localhost:2375 -N
```

And then you can connect to the local end of the tunnel like this:

``` clojure
(def client (make-client "http://localhost:2375/"))
```

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
