package algorithm;

import java.util.Objects;
import java.util.Set;

public class AssociationRule
{
    private final Set<Integer> antecedents;
    private final Set<Integer> consequents;

    public AssociationRule(Set<Integer> a, Set<Integer> c)
    {
        this.antecedents = a;
        this.consequents = c;
    }

    public boolean check(Set<Integer> input)
    {
        return !input.containsAll(this.antecedents) || input.containsAll(this.consequents);
    }

    public Set<Integer> getAntecedents()
    {
        return this.antecedents;
    }

    public Set<Integer> getConsequents()
    {
        return this.consequents;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AssociationRule that = (AssociationRule) o;
        return this.antecedents.equals(that.antecedents) && this.consequents.equals(that.consequents);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.antecedents, this.consequents);
    }

    @Override
    public String toString()
    {
        return String.format("%s ⇒ %s", this.antecedents, this.consequents);
    }
}
