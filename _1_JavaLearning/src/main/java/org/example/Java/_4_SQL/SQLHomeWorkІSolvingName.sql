USE EstablishmentHomeWork;

SELECT name FROM
(
  SELECT e.name, 
  (
    SELECT AVG(avg_rate) 
    FROM
    (
  SELECT e.name, e.chain_id, COALESCE(AVG(r.rate), 0) AS avg_rate
  FROM Establishment e
  LEFT JOIN Review rv ON e.id = rv.establishment_id
  LEFT JOIN Rate r ON rv.id = r.review_id
  WHERE e.chain_id IS NOT NULL
  GROUP BY e.id
) t
    WHERE t.chain_id = e.id
  ) AS average_rating
  FROM Establishment e
  WHERE e.is_chain = 1 AND e.chain_id IS NULL
  
  UNION
  
  SELECT e.name, AVG(r.rate) AS average_rating
  FROM Establishment e
  LEFT JOIN Review rv ON e.id = rv.establishment_id
  LEFT JOIN Rate r ON rv.id = r.review_id 
  WHERE e.chain_id IS NULL AND e.is_chain = 0
  GROUP BY e.id
) t
ORDER BY average_rating DESC;