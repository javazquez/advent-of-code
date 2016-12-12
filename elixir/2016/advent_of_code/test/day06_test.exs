# Juan Vazquez
# http://javazquez.com
# http://adventofcode.com/2016/day/6

defmodule Day06Test do
  use ExUnit.Case
  doctest Day06

  test "example input" do
    input = ["eedadn",
      "drvtee",
      "eandsr",
      "raavrd",
      "atevrs",
      "tsrnev",
      "sdttsa",
      "rasrtv",
      "nssdts",
      "ntnada",
      "svetve",
      "tesnvt",
      "vntsnd",
      "vrdear",
      "dvrsen",
      "enarar"]
      |> Day06.columns_to_rows
      |> Enum.map( fn row ->  
          Enum.max_by(row, fn(x) -> Enum.count(x) end)
        end) 
      |> Enum.map_join(&List.first/1)

    assert input == "easter"
  end

  test "from input" do
    {:ok, file_text}  = File.read "test/support/day_06_input.txt"
    input = file_text
    |> String.split(~r{\n}) 
    |> Day06.columns_to_rows
    |> Enum.map( fn row ->  
        Enum.max_by(row, fn(x) -> Enum.count(x) end)
      end) 
    |> Enum.map_join(&List.first/1)

    assert  input == "zcreqgiv"  
  end

  test "from input for solution 2" do
    {:ok, file_text}  = File.read "test/support/day_06_input.txt"
    input = file_text
    |> String.split(~r{\n}) 
    |> Day06.columns_to_rows
    |> Enum.map( fn row ->  
        Enum.min_by(row, fn(x) -> Enum.count(x) end)
      end) 
    |> Enum.map_join(&List.first/1)

    assert input  == "pljvorrk"  
  end

end

  

