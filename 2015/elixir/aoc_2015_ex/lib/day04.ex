defmodule Day04 do
  def hash_number(prefix, number) do
    :crypto.hash(:md5, "#{prefix}#{number}")
  end

  def lowest_digit(prefix, match, digit) do
    if !String.starts_with?("#{hash_number(prefix, digit) |> Base.encode16()}", match) do
      lowest_digit(prefix, match, digit + 1)
    else
      digit
    end
  end

  @spec part1_answer(binary()) :: any()
  def part1_answer(prefix) do
    lowest_digit(prefix, "00000", 0)
  end

  def part2_answer(prefix) do
    lowest_digit(prefix, "000000", 0)
  end
end
