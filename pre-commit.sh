#!/bin/sh
echo "🧼 Running spotlessApply before commit..."
./gradlew spotlessApply

if [ $? -ne 0 ]; then
  echo "❌ Spotless failed. Please fix formatting before committing."
  exit 1
fi

echo "✅ Spotless passed."