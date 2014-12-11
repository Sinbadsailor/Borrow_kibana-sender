package com.supersonic.oneoffs

import java.io.ByteArrayOutputStream
import java.net.{DatagramPacket, DatagramSocket, InetAddress}
import java.util.zip.GZIPOutputStream
import org.apache.commons.net.util.Base64

object KibanaSender extends App {

  val host = "10.100.1.193"
  val port = 12201
  val json = """{
    "version": "1.1",
    "host": "staging-bidder1b.rtb-v0.us-east-1",
    "short_message": "App ID is missing",
    "timestamp": 1417350449208,
    "level": 6,
    "_type": "BadRequest",
    "_display_request_id": "9e7f1280-788c-11e4-bb2c-edbe4a2a2047_588946469_mopub"
  }"""

  val arrOutputStream = new ByteArrayOutputStream()
  val zipOutputStream = new GZIPOutputStream(arrOutputStream)
  zipOutputStream.write(json.getBytes)
  val zipedJson = Base64.encodeBase64String(arrOutputStream.toByteArray)

  val socket = new DatagramSocket(port)
  socket.connect(InetAddress.getByName(host), port)
  val data = new DatagramPacket(zipedJson.getBytes, 0, zipedJson.length)
  socket.send(data)
}


