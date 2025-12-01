defmodule Day06 do
  def mapper(string) do
    cond do
      Regex.match?(~r/on/, string) ->
        [_, _, [x], [y], _, [x2], [y2]] = Regex.scan(~r/\w+/, string)
        {:on, x, y, x2, y2}

      Regex.match?(~r/off/, string) ->
        [_, _, [x], [y], _, [x2], [y2]] = Regex.scan(~r/\w+/, string)
        {:off, x, y, x2, y2}

      Regex.match?(~r/toggle/, string) ->
        [_, [x], [y], _, [x2], [y2]] = Regex.scan(~r/\w+/, string)
        {:toggle, x, y, x2, y2}
    end
  end

  def grid_points({action, x1, y1, x2, y2}) do
    for xs <- x1..x2, ys <- y1..y2, do: {action, xs, ys}
  end

  def control_light({:on, x, y}, grid) do
    MapSet.delete(grid, {:off, x, y})
    MapSet.put(grid, {:on, x, y})
  end

  def control_light({:off, x, y}, grid) do
    MapSet.delete(grid, {:on, x, y})
  end

  def control_light({:toggle, x, y}, grid) do
    cond do
      MapSet.member?(grid, {:on, x, y}) -> MapSet.delete(grid, {:on, x, y})
      true -> MapSet.put(grid, {:on, x, y})
    end
  end

  # find item in set and update its value
  def brightness(a, grid \\ Map.new())

  def brightness({:on, x, y}, grid) do
    Map.update(grid, {x, y}, 1, fn existing_val -> existing_val + 1 end)
  end

  def brightness({:off, x, y}, grid) do
    Map.update(grid, {x, y}, 0, fn existing_val ->
      if existing_val > 0 do
        existing_val - 1
      else
        0
      end
    end)
  end

  def brightness({:toggle, x, y}, grid) do
    Map.update(grid, {x, y}, 2, fn existing_val -> existing_val + 2 end)
  end

  def process_instructions([h | t], light_grid) do
    process_instructions(t, control_light(h, light_grid))
  end

  def process_instructions([], light_grid) do
    light_grid
  end

  def solve_par1(instructions) do
    Stream.map(instructions, fn instruct ->
      {action, x1, y1, x2, y2} =
        mapper(instruct)

      {action, String.to_integer(x1), String.to_integer(y1), String.to_integer(x2),
       String.to_integer(y2)}
    end)
    |> Stream.map(&Day06.grid_points/1)
    |> Enum.concat()
    |> process_instructions(MapSet.new())
    |> Enum.count()
  end

  def solve_part2(instructions) do
    Stream.map(instructions, fn instruct ->
      {action, x1, y1, x2, y2} =
        mapper(instruct)

      {action, String.to_integer(x1), String.to_integer(y1), String.to_integer(x2),
       String.to_integer(y2)}
    end)
    |> Stream.map(&Day06.grid_points/1)
    |> Enum.concat()
    |> Enum.reduce(%{}, fn item, acc -> brightness(item, acc) end)
    |> Map.values()
    |> Enum.sum()
  end
end
