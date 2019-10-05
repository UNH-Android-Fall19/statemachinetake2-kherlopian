package edu.newhaven.krikorherlopian.android.statemachine.states

import edu.newhaven.krikorherlopian.android.statemachine.actions.Actions
import edu.newhaven.krikorherlopian.android.statemachine.model.Sandwich

class SandwichList(val sandwiches: List<Sandwich>):
    SandwichState {
    override fun consumeAction(action: Actions): SandwichState {
        return when(action) {
            is Actions.AddSandwichClicked -> AddSandwich(sandwiches)
            is Actions.ModifySandwichSelected -> {
                ModifySandwich(sandwiches, action.sandwich)
            }
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}