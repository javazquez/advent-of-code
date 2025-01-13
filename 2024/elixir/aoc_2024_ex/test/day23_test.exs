defmodule Day23Test do
  use ExUnit.Case
  import Day23
  doctest Day23

  defp read_input(filename) do
    {:ok, contents} = File.read("input/#{filename}.txt")

    contents
    |> String.split("\n", trim: true)
    |> Enum.map(fn s ->
      String.split(s, "-")
      |> Enum.sort()
    end)
  end

  test "solve example" do
    assert 12 =
             get_triangles(read_input("day23_example"))
             |> Enum.count()

    assert 7 =
             get_triangles(read_input("day23_example"))
             |> Enum.filter(fn [x, y, z] ->
               String.starts_with?(x, "t") or String.starts_with?(y, "t") or
                 String.starts_with?(z, "t")
             end)
             |> Enum.count()
  end

  test "solve part 1" do
    assert 1175 =
             get_triangles(read_input("day23"))
             |> Enum.filter(fn [x, y, z] ->
               String.starts_with?(x, "t") or String.starts_with?(y, "t") or
                 String.starts_with?(z, "t")
             end)
             |> Enum.count()
  end

  test "solve part 2 example" do
    assert !all_connected?(["co", "de", "ta", "kh"], read_input("day23_example"))

    assert all_connected?(["co", "de", "ta"], read_input("day23_example"))
  end

  # @tag timeout: :infinity
  test "solve part 2 " do
    edges = read_input("day23")

    assert "bw,dr,du,ha,mm,ov,pj,qh,tz,uv,vq,wq,xw" = solve_part2(edges)
  end
end
