defmodule Day23 do
  use Memoize

  defmemo find_next(n, nodes) do
    Enum.filter(nodes, fn [x, y] -> x == n or y == n end)
    |> Enum.map(fn [x, y] -> if x == n, do: y, else: x end)
    |> Enum.sort()
  end

  def get_friends(node, edges) do
    find_next(node, edges)
    |> MapSet.new()
    |> MapSet.put(node)
  end

  defmemo connected?(n1, n2, edges) do
    Enum.any?(edges, fn [x, y] ->
      (x == n1 and y == n2) or
        (x == n2 and y == n1)
    end)
  end

  # start with all nodes and find the greatest
  defmemo all_connected?([], _edges) do
    false
  end

  defmemo all_connected?(nodes, edges) do
    cart = for x <- nodes, y <- nodes, x != y, do: [x, y]

    Enum.all?(cart, fn [n1, n2] ->
      connected?(n1, n2, edges)
    end)
  end

  def largest_network([], _edges, acc) do
    acc |> Enum.sort()
  end

  defmemo largest_network([h | nodes], edges, acc) do
    cond do
      all_connected?([h | acc], edges) ->
        largest_network(nodes, edges, [h | acc])

      true ->
        largest_network(nodes, edges, acc)
    end
  end

  # example triangle [a,b,c,a]
  def triangles(start, nodes) do
    comps_of_two =
      find_next(start, nodes)
      |> Enum.map(fn n -> [start, n] end)

    Enum.flat_map(comps_of_two, fn [a, b] ->
      find_next(a, nodes)
      |> Enum.map(fn n -> [n, a, b] end)
    end)
    |> Enum.flat_map(fn [a, b, c] ->
      find_next(a, nodes)
      |> Enum.map(fn n -> [n, a, b, c] end)
    end)
  end

  def get_triangles(nodes) do
    all_comps = nodes |> List.flatten() |> Enum.uniq()

    Enum.flat_map(all_comps, fn x -> triangles(x, nodes) end)
    |> Enum.filter(fn [a, _b, _c, d] -> a == d end)
    |> Enum.map(&Enum.uniq/1)
    |> Enum.reduce(MapSet.new(), fn v, acc -> MapSet.put(acc, Enum.sort(v)) end)
  end

  # use neighbors and build largest networks from those lists
  def solve_part2(edges) do
    all_comps = edges |> List.flatten() |> Enum.uniq()

    Enum.map(all_comps, fn comp ->
      get_friends(comp, edges) |> MapSet.to_list() |> Enum.sort()
    end)
    |> Enum.map(fn row -> largest_network(row, edges, []) end)
    |> Enum.sort_by(fn row -> Enum.count(row) end, :desc)
    |> Enum.uniq()
    |> Enum.take(1)
    |> Enum.at(0)
    |> Enum.join(",")
  end
end
