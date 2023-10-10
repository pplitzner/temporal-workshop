package digital.ald.temporalworkshop.temporaloutage

import com.google.protobuf.ByteString
import io.temporal.api.common.v1.Payload
import io.temporal.common.converter.EncodingKeys
import io.temporal.payload.codec.PayloadCodec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class MyCodec:PayloadCodec {

    val inputText = "abcdefghigklmnopqrstuvwxyz0123456789"
    val algorithm = "AES/CBC/PKCS5Padding"
    val key = SecretKeySpec("1234567890123456".toByteArray(), "AES")
    val iv = IvParameterSpec(ByteArray(16))

    override fun encode(payloads: MutableList<Payload>): MutableList<Payload> {
        return payloads.map { encode(it) }.toMutableList()
    }

    override fun decode(payloads: MutableList<Payload>): MutableList<Payload> {
        return payloads.map { decode(it) }.toMutableList()
    }
    private fun encode(payload: Payload): Payload {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val cipherText = cipher.doFinal(payload.toByteArray())
        return Payload.newBuilder()
            .setData(ByteString.copyFrom(cipherText))
            .build()
    }

    private fun decode(payload: Payload): Payload {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, key, iv)
        val plainText = cipher.doFinal(payload.toByteArray())
        return Payload.parseFrom(plainText)
    }

}