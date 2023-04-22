package algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class Apriori
{
    private final int minSupport;
    private final double minConfidence;

    public Apriori(int s, double c)
    {
        this.minSupport = s;
        this.minConfidence = c;
    }

    public List<AssociationRule> getRules(List<Set<Integer>> transactions)
    {
        Map<Set<Integer>, Integer> supports = new HashMap<>();
        Map<Integer, Integer> objects = new HashMap<>();

        for (Set<Integer> transaction : transactions)
            for (Integer object : transaction)
            {
                Integer value = objects.get(object);

                if (value == null)
                    value = 1;
                else
                    ++value;

                objects.put(object, value);
            }

        System.out.println(objects);

        objects.entrySet().removeIf(entry -> entry.getValue() < this.minSupport);

        Map<Set<Integer>, Integer> newSupports = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : objects.entrySet())
            newSupports.put(Collections.singleton(entry.getKey()), entry.getValue());

        for (int n = 1; !newSupports.isEmpty(); ++n)
        {
            supports.putAll(newSupports);
            Set<Set<Integer>> newItems = new HashSet<>();

            for (Set<Integer> items : newSupports.keySet())
            {
                Set<Integer> candidate = new HashSet<>(items);

                for (Integer item : objects.keySet())
                {
                    if (candidate.contains(item))
                        continue;

                    candidate.add(item);

                    boolean f = true;

                    Set<Integer> q = new HashSet<>(candidate);

                    for (Integer i : candidate)
                    {
                        q.remove(i);

                        if (!newSupports.containsKey(q))
                        {
                            f = false;
                            break;
                        }

                        q.add(i);
                    }

                    if (f)
                    {
                        Set<Integer> c = new HashSet<>(candidate);
                        newItems.add(c);
                    }

                    candidate.remove(item);
                }
            }

            newSupports.clear();

            for (Set<Integer> items : newItems)
                for (Set<Integer> transaction : transactions)
                    if (transaction.containsAll(items))
                    {
                        Integer value = newSupports.get(items);

                        if (value == null)
                            value = 1;
                        else
                            ++value;

                        newSupports.put(items, value);
                    }

            System.out.println(newSupports);
            newSupports.entrySet().removeIf(entry -> entry.getValue() < this.minSupport);
        }

        System.out.println(supports);
        System.out.println();

        Set<AssociationRule> rules = new HashSet<>();

        for (Set<Integer> items : supports.keySet())
        {
            if (items.size() < 2)
                continue;

            Set<AssociationRule> candidates = generateRules(items);

            for (AssociationRule candidate : candidates)
            {
                Set<Integer> F = new HashSet<>();

                F.addAll(candidate.getAntecedents());
                F.addAll(candidate.getConsequents());

                double confidence = 1.0 * supports.get(F) / supports.get(candidate.getAntecedents());

                System.out.printf("conf(%s) = %f%n", candidate, confidence);

                if (confidence >= this.minConfidence)
                    rules.add(candidate);
            }
        }

        System.out.println();

        return new ArrayList<>(rules);
    }

    private static Set<AssociationRule> generateRules(Set<Integer> items)
    {
        return generateRules(new HashSet<>(), items);
    }

    private static Set<AssociationRule> generateRules(Set<Integer> a, Set<Integer> c)
    {
        Set<AssociationRule> res = new HashSet<>();

        if (c.size() > 1)
            for (Integer i : c)
            {
                Set<Integer> sa = new HashSet<>(a);
                sa.add(i);

                Set<Integer> sc = new HashSet<>(c);
                sc.remove(i);

                res.add(new AssociationRule(sa, sc));
                res.addAll(generateRules(sa, sc));
            }

        return res;
    }

    @Override
    public String toString()
    {
        return String.format("Apriori: minsupport=%d, minconfidence=%f", this.minSupport, this.minConfidence);
    }
}
