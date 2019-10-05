package edu.newhaven.krikorherlopian.android.statemachine.states

import edu.newhaven.krikorherlopian.android.statemachine.actions.Actions
import edu.newhaven.krikorherlopian.android.statemachine.model.Sandwich
import edu.newhaven.krikorherlopian.android.statemachine.model.SandwichType

class ModifySandwich(private val sandwiches: List<Sandwich>, private  val sandwich: Sandwich):
    SandwichState {
    override fun consumeAction(action: Actions): SandwichState {
        return when (action) {
            is Actions.SubmitSandwichClicked -> {
                sandwich.name = action.sandwichName
                SandwichList(sandwiches)
            }
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}