package com.mlp.simpleaction

import com.mlp.sdk.MlpException
import com.mlp.sdk.MlpExecutionContext
import com.mlp.sdk.MlpPredictServiceBase
import com.mlp.sdk.MlpServiceSDK

data class SimpleTestActionRequest(
    val action: String,
    val name: String
)

class SimpleTestAction(
    override val context: MlpExecutionContext
) : MlpPredictServiceBase<SimpleTestActionRequest, String>(REQUEST_EXAMPLE, RESPONSE_EXAMPLE) {

    override fun predict(req: SimpleTestActionRequest): String {
        return when (req.action) {
            "hello" -> "Hello ${req.name}!"
            "envs" -> "Envs: ${context.environment.envsOverride}"
            else -> throw MlpException("actionUnknownException")
        }
    }

    companion object {
        val REQUEST_EXAMPLE = SimpleTestActionRequest("hello", "World")
        val RESPONSE_EXAMPLE = "Hello World!"
    }
}

fun main() {
    val actionSDK = MlpServiceSDK({ SimpleTestAction(MlpExecutionContext.systemContext) })

    actionSDK.start()
    actionSDK.blockUntilShutdown()
}
