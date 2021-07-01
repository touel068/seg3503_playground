defmodule Grades do

end

defmodule Grades.Calculator do

  def avg(marks) do
    if Enum.count(marks) == 0 do
        0
    else
      Enum.sum(marks) / Enum.count(marks)
    end
  end

  def failure_to_participate(avg_homework, avg_exams, num_labs) do
    avg_homework < 0.4 || avg_exams < 0.4 || num_labs < 3
  end

  def calculate_grade(avg_labs, avg_homework, midterm, final) do
    0.2 * avg_labs + 0.3 * avg_homework + 0.2 * midterm + 0.3 * final
  end

  def avg_exams(midterm,final) do
    (midterm+final)/2
  end

  def num_labs(labs) do
    labs
        |> Enum.reject(fn mark -> mark < 0.25 end)
        |> Enum.count()
  end

  def percentage_grade(marks) do
    :rand.uniform(100)
end

def letter_grade(marks) do
  list = ["A+","A","A-","B+","B","C+","C","D+","D","E","F"]
  Enum.random(list)
end

def numeric_grade(marks) do
  :rand.uniform(10)
end

end
