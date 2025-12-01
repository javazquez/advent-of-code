defmodule Day05 do
  @doc """
  three vowels
  one letter twice in a row
  none ab, cd, pq, or xy
  """
  def nice_string(str) do
    Regex.match?(~r/(.)\1/, str) and
      Regex.match?(~r/[aeiou].*[aeiou].*[aeiou]/, str) and
      !Regex.match?(~r/ab|cd|pq|xy/, str)
  end

  def nice_string2(str) do
    Regex.match?(~r/([A-Za-z]{2}).*\1/, str) and
      Regex.match?(~r/([A-Za-z]).\1/, str)
  end

  def part1_answer(str_lst) do
    Enum.filter(str_lst, &nice_string/1)
    |> Enum.count()
  end

  def part2_answer(str_lst) do
    Enum.filter(str_lst, &nice_string2/1)
    |> Enum.count()
  end
end
