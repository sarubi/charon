package org.wso2.charon3.core.parser;/* -----------------------------------------------------------------------------
 * Rule_segment_nz.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.5
 * Produced : Fri Jun 21 11:06:52 IST 2019
 *
 * -----------------------------------------------------------------------------
 */

import java.util.ArrayList;

final public class Rule_segment_nz extends Rule
{
  public Rule_segment_nz(String spelling, ArrayList<Rule> rules)
  {
    super(spelling, rules);
  }

  public Object accept(Visitor visitor)
  {
    return visitor.visit(this);
  }

  public static Rule_segment_nz parse(ParserContext context)
  {
    context.push("segment-nz");

    boolean parsed = true;
    int s0 = context.index;
    ParserAlternative a0 = new ParserAlternative(s0);

    ArrayList<ParserAlternative> as1 = new ArrayList<ParserAlternative>();
    parsed = false;
    {
      int s1 = context.index;
      ParserAlternative a1 = new ParserAlternative(s1);
      parsed = true;
      if (parsed)
      {
        boolean f1 = true;
        int c1 = 0;
        for (int i1 = 0; i1 < 1 && f1; i1++)
        {
          Rule rule = Rule_pchar.parse(context);
          if ((f1 = rule != null))
          {
            a1.add(rule, context.index);
            c1++;
          }
        }
        while (f1)
        {
          Rule rule = Rule_pchar.parse(context);
          if ((f1 = rule != null))
          {
            a1.add(rule, context.index);
            c1++;
          }
        }
        parsed = c1 >= 1;
      }
      if (parsed)
      {
        as1.add(a1);
      }
      context.index = s1;
    }

    ParserAlternative b = ParserAlternative.getBest(as1);

    parsed = b != null;

    if (parsed)
    {
      a0.add(b.rules, b.end);
      context.index = b.end;
    }

    Rule rule = null;
    if (parsed)
    {
        rule = new Rule_segment_nz(context.text.substring(a0.start, a0.end), a0.rules);
    }
    else
    {
        context.index = s0;
    }

    context.pop("segment-nz", parsed);

    return (Rule_segment_nz)rule;
  }
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
