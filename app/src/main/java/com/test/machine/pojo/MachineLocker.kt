package com.test.machine.pojo

data class MachineLocker(
    val active: Boolean,
    val columnSequence: Int,
    val id: Int,
    val lockerNo: Int,
    val lockerSequenceNo: Int,
    val machineID: Int,
    val machineName: String,
    val modelName: String,
    val size: Int
)