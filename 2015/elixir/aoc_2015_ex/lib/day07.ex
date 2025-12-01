defmodule Day07 do
  import Bitwise
  use Memoize

  def create_map(string, mp \\ %{}) do
    cond do
      Regex.match?(~r/^\d+ -> \w+/, string) ->
        [[x], [y]] = Regex.scan(~r/\w+/, string)
        Map.put(mp, y, x)

      Regex.match?(~r/^\w+ -> \w+/, string) ->
        [[x], [y]] = Regex.scan(~r/\w+/, string)
        Map.put(mp, y, x)

      Regex.match?(~r/AND/, string) ->
        [[x], _, [y], [z]] = Regex.scan(~r/\w+/, string)
        Map.put(mp, z, {:and, x, y})

      Regex.match?(~r/NOT/, string) ->
        [_, [x], [y]] = Regex.scan(~r/\w+/, string)
        Map.put(mp, y, {:not, x})

      Regex.match?(~r/RSHIFT/, string) ->
        [[x], _, [y], [z]] = Regex.scan(~r/\w+/, string)
        Map.put(mp, z, {:rshift, x, y})

      Regex.match?(~r/LSHIFT/, string) ->
        [[x], _, [y], [z]] = Regex.scan(~r/\w+/, string)
        Map.put(mp, z, {:lshift, x, y})

      Regex.match?(~r/OR/, string) ->
        [[x], _, [y], [z]] = Regex.scan(~r/\w+/, string)
        Map.put(mp, z, {:or, x, y})
    end
  end

  defmemo get_wire_value(mp, {:and, x, y}) do
    get_wire_value(mp, x) &&& get_wire_value(mp, y)
  end

  defmemo get_wire_value(mp, {:not, x}) do
    bnot(get_wire_value(mp, x))
  end

  defmemo get_wire_value(mp, {:rshift, x, y}) do
    get_wire_value(mp, x) >>> get_wire_value(mp, y)
  end

  defmemo get_wire_value(mp, {:lshift, x, y}) do
    get_wire_value(mp, x) <<< get_wire_value(mp, y)
  end

  defmemo get_wire_value(mp, {:or, x, y}) do
    bor(get_wire_value(mp, x), get_wire_value(mp, y))
  end

  defmemo get_wire_value(mp, wire) do
    if Regex.match?(~r/^\d+$/, wire) do
      String.to_integer(wire)
    else
      get_wire_value(mp, Map.get(mp, wire))
    end
  end

  def solve_part1(instructions) do
    instructions
    |> Enum.reduce(%{}, fn instruction, acc -> Map.merge(acc, create_map(instruction)) end)
    |> get_wire_value("a")
  end

  def solve_part2(instructions) do
    instructions
    |> Enum.reduce(%{}, fn instruction, acc -> Map.merge(acc, create_map(instruction)) end)
    |> Map.put("b", "46065")
    |> get_wire_value("a")
  end
end
