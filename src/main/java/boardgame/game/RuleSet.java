package boardgame.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuleSet<T extends Board> implements Iterable<Rule<T>> {

	private List<Rule<T>> rules = new ArrayList<>();

	public List<Rule<T>> getRules() {
		return this.rules;
	}

	public void add(Rule<T> rule) {
		rules.add(rule);
	}

	@Override
	public Iterator<Rule<T>> iterator() {
		return rules.iterator();
	}

}
