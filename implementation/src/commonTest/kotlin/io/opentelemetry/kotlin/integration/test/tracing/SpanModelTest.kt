package io.opentelemetry.kotlin.integration.test.tracing

import io.opentelemetry.kotlin.ExperimentalApi
import io.opentelemetry.kotlin.InstrumentationScopeInfoImpl
import io.opentelemetry.kotlin.clock.FakeClock
import io.opentelemetry.kotlin.resource.FakeResource
import io.opentelemetry.kotlin.tracing.FakeSpanContext
import io.opentelemetry.kotlin.tracing.fakeSpanLimitsConfig
import io.opentelemetry.kotlin.tracing.model.SpanKind
import io.opentelemetry.kotlin.tracing.model.SpanModel
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalUnsignedTypes::class)
internal class SpanModelTest {

    @OptIn(ExperimentalApi::class)
    @Test
    fun testEndWithNullProcessor() {
        val spanModel = SpanModel(
            clock = FakeClock(),
            processor = null,
            name = "test-span",
            spanKind = SpanKind.INTERNAL,
            startTimestamp = 0L,
            instrumentationScopeInfo = InstrumentationScopeInfoImpl("test", null, null, emptyMap()),
            resource = FakeResource(),
            parent = FakeSpanContext.INVALID,
            spanContext = FakeSpanContext.VALID,
            spanLimitConfig = fakeSpanLimitsConfig
        )

        spanModel.end()

        assertTrue(spanModel.hasEnded)
    }
}
