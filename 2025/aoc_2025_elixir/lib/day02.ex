defmodule Day02 do
  def invalid_id(id) do
    repeated_seq = ~r/^(.+)\1$/
    if Regex.match?(repeated_seq, id), do: true, else: false
  end

  def invalid2(id) do
    repeated_seq = ~r/^(.+)\1+$/
    subs = Regex.scan(repeated_seq, id)
    if Enum.count(subs) > 0, do: true, else: false
  end

  def part1(input_ranges) do
    input_ranges
    |> Enum.flat_map(fn list ->
      list |> Enum.filter(fn id -> invalid_id(Integer.to_string(id)) end)
    end)
    |> Enum.sum()
  end

  def part2(input_ranges) do
    input_ranges
    |> Enum.flat_map(fn list ->
      list |> Enum.filter(fn id -> invalid2(Integer.to_string(id)) end)
    end)
    |> Enum.sum()
  end
end
