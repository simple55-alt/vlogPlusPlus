package com.vlogplusplus.videoEditor.interfaces

import com.vlogplusplus.videoEditor.fragments.OptiBaseCreatorDialogFragment
import java.io.File

interface OptiDialogueHelper {
    fun setHelper(helper: OptiBaseCreatorDialogFragment.CallBacks)
    fun setMode(mode: Int)
    fun setFilePathFromSource(file: File)
    fun setDuration(duration: Long)
}
