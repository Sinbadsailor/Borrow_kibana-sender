package com.supersonic.oneoffs

object KibanaSender extends App {

  org.slf4j.MDC.put("type", "Bad Request")
  org.slf4j.LoggerFactory.getLogger("potato").warn("boo")
}
