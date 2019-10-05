package edu.newhaven.krikorherlopian.android.statemachine.states

import edu.newhaven.krikorherlopian.android.statemachine.actions.Actions
import edu.newhaven.krikorherlopian.android.statemachine.model.Sandwich

class AddSandwich(private val sandwiches: List<Sandwich>):
    SandwichState {
    override fun consumeAction(action: Actions): SandwichState {
        return when (action) {
            is Actions.SandwichTypeSelected -> NameSandwich(sandwiches, action.type)
            is Actions.PredefinedSandwichSelected -> SandwichList(sandwiches + action.sandwich)
            else -> throw IllegalStateException("Invalid action $action passed to state $this")
        }
    }
}