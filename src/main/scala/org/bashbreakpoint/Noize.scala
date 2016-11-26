package org.bashbreakpoint

import scala.util.Random
import org.hive2hive.core.api.H2HNode
import org.hive2hive.core.api.configs.{FileConfiguration, NetworkConfiguration}
import org.hive2hive.core.api.interfaces.{IFileConfiguration, IH2HNode}
import java.net.InetAddress
import scala.collection.JavaConversions._
import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher

object Noize {

  def main(args: Array[String]) {
    val fileConfiguration = FileConfiguration.createDefault

    val node = H2HNode.createNode(fileConfiguration)

    node.connect(NetworkConfiguration.createInitial)

    while(true) {

      val nodePeers = node.getPeer.peer.peerBean.peerMap.all.toList
      nodePeers.foreach { pa =>
        println(s"Sending message to $pa")
        node.getPeer.peer.sendDirect(pa).`object`(makeNoize).start()
      }

      Thread.sleep(1500)
    }

  }

  def makeNoize: Array[Byte] = {
    val keyBytes = Array.ofDim[Byte](16)
    Random.nextBytes(keyBytes)

    val key = new SecretKeySpec(keyBytes, "AES")

    val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val garbage = randomBytes

    cipher.doFinal(garbage)
  }

  def randomBytes: Array[Byte] = {
    val size = Random.nextInt(2048) + 1
    Seq.fill(size)(Random.nextPrintableChar).mkString.getBytes
  }
}