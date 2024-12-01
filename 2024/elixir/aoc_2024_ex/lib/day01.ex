defmodule Day01 do
  @moduledoc """
  Documentation for `Day01ex`.
  https://adventofcode.com/2024/day/1
  """

  def calculate_distance(a, b) do
    abs(a - b)
  end

  def calculate_list([], acc) do
    acc
  end

  def calculate_list([{a, b} | t], acc) do
    calculate_list(t, acc + calculate_distance(a, b))
  end

  @doc """
    Score is the frequency_count * number, if not found it is zero
  """
  def calc_similarity_score(number, freq_map) do
    number * Map.get(freq_map, number, 0)
  end

  def calculate_all_similarity_scores([], _fmap, acc) do
    acc
  end

  def calculate_all_similarity_scores([h | t], fmap, acc) do
    calculate_all_similarity_scores(t, fmap, acc + calc_similarity_score(h, fmap))
  end

  def part1_answer(number_list_a, number_list_b) do
    a_sorted = Enum.sort(number_list_a)
    b_sorted = Enum.sort(number_list_b)
    list = Enum.zip(a_sorted, b_sorted)
    calculate_list(list, 0)
  end

  def part2_answer(number_list_a, number_list_b) do
    freqs = Enum.frequencies(number_list_b)
    calculate_all_similarity_scores(number_list_a, freqs, 0)
  end
end
