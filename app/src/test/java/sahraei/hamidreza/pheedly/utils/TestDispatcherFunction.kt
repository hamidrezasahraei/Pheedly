package sahraei.hamidreza.pheedly.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
fun runStandardTest(block: suspend () -> Unit) {
    runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        block.invoke()
        Dispatchers.resetMain()
    }
}