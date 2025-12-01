defmodule Day09 do
  def get_places(flight) do
    [[a, b, d]] =
      Regex.scan(~r/(\w+) to (\w+) = (\d+)/, flight, capture: :all_but_first)

    [a, b, d]
  end

  def create_flights(flight) do
    [[a, b, d]] =
      Regex.scan(~r/(\w+) to (\w+) = (\d+)/, flight, capture: :all_but_first)

    %{[a, b] => String.to_integer(d)} |> Map.merge(%{[b, a] => String.to_integer(d)})
  end

  def calc_flight_path([departure, destination | []], flights, total) do
    Map.get(flights, [departure, destination]) + total
  end

  def calc_flight_path([departure, destination | path], flights, total) do
    calc_flight_path(
      [destination | path],
      flights,
      Map.get(flights, [departure, destination]) + total
    )
  end

  def calc_all_flights(lines) do
    places =
      lines
      |> Enum.map(&get_places/1)

    flights =
      lines
      |> Enum.map(&create_flights/1)
      |> Enum.reduce(%{}, &Map.merge/2)

    destinations =
      Enum.flat_map(places, fn [a, b, _] -> [a, b] end) |> MapSet.new() |> MapSet.to_list()

    Combination.permutate(destinations)
    |> Enum.map(fn lst -> calc_flight_path(lst, flights, 0) end)
  end

  def solve_part1(lines) do
    calc_all_flights(lines)
    |> Enum.min()
  end

  def solve_part2(lines) do
    calc_all_flights(lines)
    |> Enum.max()
  end
end
