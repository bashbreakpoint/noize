# noize

This program is intended to do the following:
1. Discover a Peer 2 Peer network
1. For each peer discovered (up to a limit)
    1. Create a random length string
    1. Encrypt said string using aes-256 bit encryption, using a randomly generated salt and password.
    1. Send that peer the newly encrypted string
1. Each peer that recieves these encrypted strings does the following:
    1. Throws it away!

## Wut? Why?
The whole purpose of Noize is to create encrypted noise across the internet. 
Many, many, many people are trying to collect and store everything we do online all the time.
But the listeners assume that we aren't malicious enough to generate fake, encrypted data all the time.

Well, I'm malicious enough.

If you are also malicious enough, get involved. Run it.

Make some Noize.

## TODO
This program isn't quite ready to run yet. As far as I know right now, the TODO list is as follows:

- [ ] Create a discovery layer. I thought the p2p lib did that, but it didn't. Do discovery over UDP.
- [ ] Limit the number of peers to connect to to something reasonable (possibly configurable?). Figure out how to know when a peer has disconnected on the other side. I suspect I can use `node.isConnected`, but I'll have to double check that.
- [ ] Set up logging that can be turned off/on
- [ ] Figure out if/how to set a 200 response, so 404s (which is what is responds with now for the direct send) can't become a red flag.
- [ ] Test it with some docker containers and people I know.
