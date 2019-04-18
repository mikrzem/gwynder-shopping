package pl.net.gwynder.shopping.common.errors

class InvalidFormat(type: String) : RuntimeException("Invalid $type format")