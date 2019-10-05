package edu.newhaven.krikorherlopian.android.statemachine.states

import edu.newhaven.krikorherlopian.android.statemachine.actions.Actions


interface SandwichState {
    fun consumeAction(action: Actions): SandwichState
}