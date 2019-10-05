package edu.newhaven.krikorherlopian.android.statemachine.states

import edu.newhaven.krikorherlopian.android.statemachine.actions.Actions
import edu.newhaven.krikorherlopian.android.statemachine.model.Sandwich
import edu.newhaven.krikorherlopian.android.statemachine.model.SandwichType


class NameSandwich(private val sandwiches: List<Sandwich>, private val newSandwichType: SandwichType):
    SandwichState {
    override fun consumeAction(action: Actions): SandwichState {
        return when (action) {
            is Actions.SubmitSandwichClicked -> {
                val newSandwich = Sandwich(action.sandwichName, newSandwichType)
                SandwichList(sandwiches + newSandwich)
            }
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}